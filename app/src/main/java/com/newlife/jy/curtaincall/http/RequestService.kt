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

import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import com.newlife.jy.curtaincall.dataBean.Repo
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RequestService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>>

    @Multipart
    @POST("xxxx/xxxx") //This is imaginary URL
    fun updateImage(@Part("name") name: RequestBody,
                    @Part image: MultipartBody.Part): Observable<String>

    //?page={page}&maxResult={maxResult}&showapi_appid=$APPID&showapi_sign=$SECRET

    @GET("958-1")
    fun getKBMHRepos(@QueryMap page: Map<String, String>): Observable<HorrorComicBean.HorrorComic>
}
