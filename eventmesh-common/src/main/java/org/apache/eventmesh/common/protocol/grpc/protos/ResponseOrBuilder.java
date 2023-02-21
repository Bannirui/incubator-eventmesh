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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: eventmesh-client.proto

package org.apache.eventmesh.common.protocol.grpc.protos;

@SuppressWarnings({"all"})
public interface ResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:eventmesh.common.protocol.grpc.Response)
    com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string respCode = 1;</code>
     */
    String getRespCode();

    /**
     * <code>string respCode = 1;</code>
     */
    com.google.protobuf.ByteString
    getRespCodeBytes();

    /**
     * <code>string respMsg = 2;</code>
     */
    String getRespMsg();

    /**
     * <code>string respMsg = 2;</code>
     */
    com.google.protobuf.ByteString
    getRespMsgBytes();

    /**
     * <code>string respTime = 3;</code>
     */
    String getRespTime();

    /**
     * <code>string respTime = 3;</code>
     */
    com.google.protobuf.ByteString
    getRespTimeBytes();
}
