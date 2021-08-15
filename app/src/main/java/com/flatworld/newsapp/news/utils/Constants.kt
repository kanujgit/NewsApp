package com.flatworld.newsapp.news.utils

class  Constants {

    companion object CompanionObject {
        /**  Extract the key associated with the JSONObject  */
        val JSON_KEY_RESPONSE = "response"
        val JSON_KEY_RESULTS = "results"
        val JSON_KEY_WEB_TITLE = "webTitle"
        val JSON_KEY_SECTION_NAME = "sectionName"
        val JSON_KEY_WEB_PUBLICATION_DATE = "webPublicationDate"
        val JSON_KEY_WEB_URL = "webUrl"
        val JSON_KEY_TAGS = "tags"
        val JSON_KEY_FIELDS = "fields"
        val JSON_KEY_THUMBNAIL = "thumbnail"
        val JSON_KEY_TRAIL_TEXT = "trailText"

        /** Read timeout for setting up the HTTP request  */
        val READ_TIMEOUT = 10000 /* milliseconds */

        /** Connect timeout for setting up the HTTP request  */
        val CONNECT_TIMEOUT = 15000 /* milliseconds */

        /** HTTP response code when the request is successful  */
        val SUCCESS_RESPONSE_CODE = 200

        /** Request method type "GET" for reading information from the server  */
        val REQUEST_METHOD_GET = "GET"

        /** URL for news data from the guardian data set  */
        val NEWS_REQUEST_URL = "https://content.guardianapis.com/search"

        /** Parameters  */
        val QUERY_PARAM = "q"
        val ORDER_BY_PARAM = "order-by"
        val PAGE_SIZE_PARAM = "page-size"
        val ORDER_DATE_PARAM = "order-date"
        val FROM_DATE_PARAM = "from-date"
        val SHOW_FIELDS_PARAM = "show-fields"
        val FORMAT_PARAM = "format"
        val SHOW_TAGS_PARAM = "show-tags"
        val API_KEY_PARAM = "api-key"
        val SECTION_PARAM = "section"

        /** The show fields we want our API to return  */
        val SHOW_FIELDS = "thumbnail,trailText"

        /** The format we want our API to return  */
        val FORMAT = "json"

        /** The show tags we want our API to return  */
        val SHOW_TAGS = "contributor"

        /** API Key  */
        val API_KEY = "test" // Use your API Key when API rate limit exceeded


        /** Default number to set the image on the top of the textView  */
        val DEFAULT_NUMBER = 0

        /** Constants value for each fragment  */
        val WORLD = 0
        val GENERAL = 1
        val HEALTH = 2
        val SCIENCE = 3
        val SPORTS = 4
        val TECHNOLOGY = 5
        val BUSINESS = 6
        val ENTERTAINMENT = 7

    }

}