/*******************************************************************************
 * Copyright 2017 Yuran Zhang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.newlife.jy.curtaincall.http

import android.content.Context
import android.support.annotation.StringRes
import com.newlife.jy.curtaincall.R

enum class ApiErrorType(val code: Int, @param: StringRes private val messageId: Int) {
    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found),
    CONNECTION_TIMEOUT(408, R.string.timeout),
    NETWORK_NOT_CONNECT(499, R.string.network_wrong),
    UNEXPECTED_ERROR(700, R.string.unexpected_error),

    SYSTEM_ERROR(-1, R.string.system_error),
    NO_BALANCE(-2, R.string.no_balance),
    READ_TIMEOUT(-3, R.string.read_timeout),
    DATA_PARSE_ERROR(-4, R.string.data_parse_error),
    DNS_PARSE_ERROR(-5, R.string.dns_parse_error),
    NO_SERVICE(-6, R.string.no_service),

    SYSTEM_UNDER_MAINTENANCE(1000, R.string.system_under_maintenance),
    NEED_APPID(1002, R.string.need_appid),
    NEED_SIGN(1003, R.string.need_sign),
    SIGN_ERROR(1004, R.string.sign_error),
    TIMESTAMP_OUT_OF_DATE(1005, R.string.timestamp_out_of_date),
    NO_PERMISSION(1006, R.string.no_permission),
    NO_ORDER(1007, R.string.no_order),
    PERMISSION_CLOSED(1008, R.string.permission_closed),
    RATE_LIMITED(1009, R.string.rate_limited),
    APP_NOT_FIND(1010, R.string.app_not_find),
    CHILD_PERMISSION_INVALID(1011, R.string.child_permission_invalid),
    CHILD_PERMISSION_OUT_OF_DATE(1012, R.string.child_permission_out_of_date),
    CHILD_PERMISSION_LIMITED(1013, R.string.child_permission_limited);

    private val DEFAULT_CODE = 1

    fun getApiErrorModel(context: Context): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, context.getString(messageId))
    }
}
