/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geektimes.web.rest.client;

import org.geektimes.web.rest.core.DefaultResponse;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * HTTP GET Method {@link Invocation}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
class HttpPostInvocation implements Invocation {

    private final URI uri;

    private final URL url;

    private final Entity<?> entity;

    private final MultivaluedMap<String, Object> headers;

    HttpPostInvocation(URI uri,
                       MultivaluedMap<String, Object> headers,
                       Entity<?> entity) {
        this.uri = uri;
        this.headers = headers;
        this.entity = entity;
        try {
            this.url = uri.toURL();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Invocation property(String name, Object value) {
        return this;
    }

    @Override
    public Response invoke() {
        HttpURLConnection connection = null;
        try {

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.POST);
            setRequestHeaders(connection);
            connection.setUseCaches(false);//设置不要缓存
            connection.setInstanceFollowRedirects(true);
            connection.setDoInput(true);
            connection.setDoOutput(true);


            String contentType = String.format("%s/%s", entity.getVariant().getMediaType().getType(),
                    entity.getVariant().getMediaType().getSubtype());
            connection.setRequestProperty("Content-Type", contentType);

            String payload = null;


            if (contentType.equals(MediaType.APPLICATION_FORM_URLENCODED)) {
                Form form = (Form) entity.getEntity();
                MultivaluedMap<String, String> formData = form.asMap();

                // TODO: only processing one value now
                payload = formData.keySet().stream()
                        .map(key -> key + "=" + encodeValue(formData.get(key).get(0)))
                        .collect(Collectors.joining("&"));
            } else {
                // TODO: other content types
            }

            byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", String.valueOf(payloadBytes.length));
            try( OutputStream os = connection.getOutputStream()) {
                os.write(payloadBytes);
                os.flush();
            }

            // TODO Set the cookies
            int statusCode = connection.getResponseCode();
            DefaultResponse response = new DefaultResponse();
            response.setConnection(connection);
            response.setStatus(statusCode);
            return response;


        } catch (IOException e) {
            // TODO Error handler
        }
        return null;
    }

    private String encodeValue(String value)  {
        String res = null;
        try {
            res = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Throwable throwable) {

        }
        return res;
    }

    private void setRequestHeaders(HttpURLConnection connection) {
        if ( headers == null || headers.isEmpty()) return;
        for (Map.Entry<String, List<Object>> entry : headers.entrySet()) {
            String headerName = entry.getKey();
            for (Object headerValue : entry.getValue()) {
                connection.setRequestProperty(headerName, headerValue.toString());
            }
        }
    }

    @Override
    public <T> T invoke(Class<T> responseType) {
        Response response = invoke();
        return response.readEntity(responseType);
    }

    @Override
    public <T> T invoke(GenericType<T> responseType) {
        Response response = invoke();
        return response.readEntity(responseType);
    }

    @Override
    public Future<Response> submit() {
        return null;
    }

    @Override
    public <T> Future<T> submit(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> Future<T> submit(GenericType<T> responseType) {
        return null;
    }

    @Override
    public <T> Future<T> submit(InvocationCallback<T> callback) {
        return null;
    }
}
