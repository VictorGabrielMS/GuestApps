package com.example.guests.core

class Constants private constructor() {

    /**
     * all constants used on database
     * refer to tables and columns
     */
    object DATABASE {
        const val NAME = "Guests"

        /**
         * tables available on database
         */
        object TABLES {
            object GUEST {
                const val NAME = "guests"

                object COLUMNS {
                    const val ID = "id"
                    const val NAME = "name"
                    const val PRESENCE = "presence"
                }
            }
        }
    }


    /**
     * all constants ui
     * refer navigation params and filter types
     */
    object GUESTDEFS {
        // used as navigation param
        const val GUESTID = "guestId"

        // used as filter to load data
        object FILTER {
            const val ABSENTS = 0
            const val PRESENTS = 1
            const val ALL = 2
        }
    }
}