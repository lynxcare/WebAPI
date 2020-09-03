/*
 *
 * Copyright 2017 Observational Health Data Sciences and Informatics
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Mikhail Mironov
 *
 */
package org.ohdsi.webapi.shiro.filters;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.ohdsi.webapi.shiro.management.AtlasSecurity.AUTH_CLIENT_OPENID;


public class SendTokenInRedirectFilter extends AdviceFilter {
    private String redirectUrl;

    public SendTokenInRedirectFilter(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        String jwt = (String)request.getAttribute("TOKEN");
        try {
            ((HttpServletResponse) response).sendRedirect(redirectUrl + "/" + AUTH_CLIENT_OPENID + "/" + jwt);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
