package com.ahaohuo.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author jzy
 * Date 2016/8/29
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = null;
        try {
            Reader reader = new StringReader(value.string());
            jsonReader = gson.newJsonReader(reader);
            jsonReader.setLenient(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            value.close();
        }
        return adapter.read(jsonReader);
    }
}
