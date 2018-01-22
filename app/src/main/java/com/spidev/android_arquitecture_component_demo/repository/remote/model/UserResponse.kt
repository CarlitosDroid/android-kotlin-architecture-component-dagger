package com.spidev.android_arquitecture_component_demo.repository.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */

class UserResponse {


    /**
     * id : 1
     * name : Human Made
     * url :
     * description :
     * link : https://demo.wp-api.org/author/humanmade/
     * slug : humanmade
     * avatar_urls : {"24":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=24&d=mm&r=g","48":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=48&d=mm&r=g","96":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=96&d=mm&r=g"}
     * meta : []
     * _links : {"self":[{"href":"https://demo.wp-api.org/wp-json/wp/v2/users/1"}],"collection":[{"href":"https://demo.wp-api.org/wp-json/wp/v2/users"}]}
     */

    var id: Int = 0
    var name: String? = null
    var url: String? = null
    var description: String? = null
    var link: String? = null
    var slug: String? = null
    var avatar_urls: AvatarUrlsBean? = null
    var _links: LinksBean? = null
    var meta: List<*>? = null

    class AvatarUrlsBean {
        /**
         * 24 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=24&d=mm&r=g
         * 48 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=48&d=mm&r=g
         * 96 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=96&d=mm&r=g
         */
        @SerializedName("24")
        var `_$24`: String? = null
        @SerializedName("48")
        var `_$48`: String? = null
        @SerializedName("96")
        var `_$96`: String? = null
    }

    class LinksBean {
        var self: List<SelfBean>? = null
        var collection: List<CollectionBean>? = null

        class SelfBean {
            /**
             * href : https://demo.wp-api.org/wp-json/wp/v2/users/1
             */

            var href: String? = null
        }

        class CollectionBean {
            /**
             * href : https://demo.wp-api.org/wp-json/wp/v2/users
             */

            var href: String? = null
        }
    }
}
