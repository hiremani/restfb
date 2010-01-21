/*
 * Copyright (c) 2010 Mark Allen.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.restfb;

import java.io.IOException;

/**
 * Specifies how a class that sends {@code POST}s to the Facebook API endpoint
 * must operate.
 * 
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public interface WebRequestor {
  /**
   * Encapsulates an HTTP response body and status code.
   * 
   * @author <a href="http://restfb.com">Mark Allen</a>
   */
  static class Response {
    /**
     * HTTP response status code (e.g. 200).
     */
    private Integer statusCode;

    /**
     * HTTP response body as text.
     */
    private String body;

    /**
     * Creates a response with the given HTTP status code and response body as
     * text.
     * 
     * @param statusCode
     *          The HTTP status code of the response.
     * @param body
     *          The response body as text.
     * @throws NullPointerException
     *           If {@code statusCode} is {@code null}.
     */
    public Response(Integer statusCode, String body) {
      if (statusCode == null)
        throw new NullPointerException("Response status code cannot be null.");

      this.statusCode = statusCode;
      this.body = StringUtils.trimToEmpty(body);
    }

    /**
     * Gets the HTTP status code.
     * 
     * @return The HTTP status code.
     */
    public Integer getStatusCode() {
      return statusCode;
    }

    /**
     * Gets the HTTP response body as text.
     * 
     * @return The HTTP response body as text.
     */
    public String getBody() {
      return body;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      if (StringUtils.isBlank(getBody()))
        return String.format("HTTP status code %d and an empty response body.",
          getStatusCode());
      return String.format("HTTP status code %d and response body: %s",
        getStatusCode(), getBody());
    }
  }

  /**
   * Given a Facebook API endpoint URL and parameter string, execute a {@code
   * POST} to the endpoint URL.
   * 
   * @param url
   *          The URL to {@code POST} to.
   * @param parameters
   *          The parameters to be {@code POST}ed.
   * @return HTTP response data.
   * @throws IOException
   *           If an error occurs while performing the {@code POST}.
   */
  Response executePost(String url, String parameters) throws IOException;
}