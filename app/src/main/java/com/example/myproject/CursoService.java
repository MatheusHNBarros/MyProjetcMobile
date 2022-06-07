package com.example.myproject;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CursoService {

    @POST("courses")
    Call<CursoResponse> createRequestPost(@Body CursoPost cursoPost);

    @GET("courses")
    Call<List<CursoResponse>> getAllCourse();

    @PUT("Courses/{course_id}")
    Call<CursoResponse> createRequestPut(@Body CursoPost cursoPut, @Path("course_id") int id);
}
