package com.spidev.android_arquitecture_component_demo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.spidev.android_arquitecture_component_demo.repository.remote.api.ApiResponse;
import com.spidev.android_arquitecture_component_demo.vo.AppExecutors;
import com.spidev.android_arquitecture_component_demo.vo.Resource;

import java.util.Objects;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán Leonardo Camilo Vargas Huamán on 1/7/18.
 */
/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 *
 * @param <ResultType>
 * @param <RequestType>
 */
// ResultType: Type for the Resource data
// RequestType: Type for the API response
public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final AppExecutors appExecutors;

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        result.setValue(Resource.Companion.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> setValue(Resource.Companion.success(newData)));
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (!Objects.equals(result.getValue(), newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<RequestType>> apiResponseLiveData = createCall();
        result.addSource(dbSource, newData -> setValue(Resource.Companion.loading(newData)));
        result.addSource(apiResponseLiveData, requestTypeApiResponse -> {
            result.removeSource(apiResponseLiveData);
            result.removeSource(dbSource);

            if (requestTypeApiResponse.isSuccessful()) {
                appExecutors.diskIO().execute(() -> {

                    saveCallResult(processResponse(requestTypeApiResponse));
                    appExecutors.mainThread().execute(() -> {
                        result.addSource(loadFromDb(), newData -> setValue(Resource.Companion.success(newData)));
                    });

                });
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setValue(Resource.Companion.error(requestTypeApiResponse.errorMessage, newData)));
            }
        });
    }

    protected void onFetchFailed() {
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();
}
