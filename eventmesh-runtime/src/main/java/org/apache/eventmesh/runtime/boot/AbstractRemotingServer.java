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

package org.apache.eventmesh.runtime.boot;

import org.apache.eventmesh.common.EventMeshThreadFactory;
import org.apache.eventmesh.common.utils.ThreadUtils;

import java.util.concurrent.TimeUnit;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRemotingServer {

    private static final int DEFAULT_SLEEP_SECONDS = 30;
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    private EventLoopGroup bossGroup;
    private EventLoopGroup ioGroup;
    private EventLoopGroup workerGroup;
    private int port;

    public EventLoopGroup getBossGroup() {
        return bossGroup;
    }

    public void setBossGroup(final EventLoopGroup bossGroup) {
        this.bossGroup = bossGroup;
    }

    public EventLoopGroup getIoGroup() {
        return ioGroup;
    }

    public void setIoGroup(final EventLoopGroup ioGroup) {
        this.ioGroup = ioGroup;
    }

    public EventLoopGroup getWorkerGroup() {
        return workerGroup;
    }

    public void setWorkerGroup(final EventLoopGroup workerGroup) {
        this.workerGroup = workerGroup;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    private EventLoopGroup initBossGroup(final String threadPrefix) {
        bossGroup = new NioEventLoopGroup(1, new EventMeshThreadFactory(threadPrefix + "-boss", true));
        return bossGroup;
    }

    private EventLoopGroup initIOGroup(final String threadPrefix, final int threadNum) {
        ioGroup = new NioEventLoopGroup(threadNum, new EventMeshThreadFactory(threadPrefix + "-io"));
        return ioGroup;
    }

    private EventLoopGroup initWorkerGroup(final String threadPrefix, final int threadNum) {
        workerGroup = new NioEventLoopGroup(threadNum, new EventMeshThreadFactory(threadPrefix + "-worker"));
        return workerGroup;
    }

    public void init(final String threadPrefix) throws Exception {
        initBossGroup(threadPrefix);
        initIOGroup(threadPrefix, MAX_THREADS);
        initWorkerGroup(threadPrefix, MAX_THREADS);
    }

    public void shutdown() throws Exception {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
            if (log.isInfoEnabled()) {
                log.info("shutdown bossGroup");
            }
        }

        ThreadUtils.randomPause(TimeUnit.SECONDS.toMillis(DEFAULT_SLEEP_SECONDS));

        if (ioGroup != null) {
            ioGroup.shutdownGracefully();
            if (log.isInfoEnabled()) {
                log.info("shutdown ioGroup");
            }
        }

        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
            if (log.isInfoEnabled()) {
                log.info("shutdown workerGroup");
            }
        }
    }

    public abstract void start() throws Exception;
}
