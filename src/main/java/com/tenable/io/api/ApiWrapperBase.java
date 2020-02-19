package com.tenable.io.api;


import java.net.MalformedURLException;

import com.tenable.io.core.exceptions.TenableIoException;
import com.tenable.io.core.services.AsyncHttpService;
import com.tenable.io.core.utilities.UriBuilderHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (c) 2017 Tenable Network Security, Inc.
 */
@Slf4j
public abstract class ApiWrapperBase {
    /**
     * The Async http service.
     */
    protected final AsyncHttpService asyncHttpService;
    /**
     * The Api scheme.
     */
    protected final String apiScheme;
    /**
     * The Api host.
     */
    protected final String apiHost;
    /**
     * The Container security host.
     */
    protected final String containerSecurityBaseUrl = "/container-security/api/v1";

    /**
     * Instantiates a new Api wrapper base.
     *
     * @param asyncHttpService the AsyncHttpService
     * @param apiScheme the Tenable IO scheme
     * @param ApiHost the Tenable IO host
     */
    public ApiWrapperBase( AsyncHttpService asyncHttpService, String apiScheme, String ApiHost ) {
        this.asyncHttpService = asyncHttpService;
        this.apiScheme = apiScheme;
        apiHost = ApiHost;
    }


    /**
     * Creates a new UriBuilderHelper initialized with the API scheme and host
     *
     * @return a new UriBuilderHelper initialized with the API scheme and host
     */
    protected UriBuilderHelper createBaseUriBuilder() {
        return new UriBuilderHelper( apiScheme, apiHost );
    }


    /**
     * Creates a new UriBuilderHelper initialized with the API scheme, host and path
     *
     * @param path the path
     * @return a new UriBuilderHelper initialized with the API scheme, host and path
     */
    protected UriBuilderHelper createBaseUriBuilder( String path ) {
        return new UriBuilderHelper( apiScheme, apiHost, path );
    }

    public String getUriString(String path) {
        try {
            return createBaseUriBuilder(path).build().toURL().toString();
        } catch (MalformedURLException | TenableIoException e) {
            throw new RuntimeException("Error building uri string", e);
        }
    }
}
