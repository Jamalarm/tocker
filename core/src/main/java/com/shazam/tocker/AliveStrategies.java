/*
 * Copyright 2015 Shazam Entertainment Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shazam.tocker;

import java.util.function.Function;

public class AliveStrategies {
    public static AliveStrategy retrying(Function<RunningDockerInstance, Boolean> upCheck, int timesToTry, int millisBetweenRetry) {
        return (runningInstance) -> {
            while (timesToTry > 0 && !upCheck.apply(runningInstance)) {
                try {
                    Thread.sleep(millisBetweenRetry);
                } catch (InterruptedException e) {
                    // oh really?!
                }
            }
        };
    }

    public static AliveStrategy alwaysAlive() {
        return (runningInstance) -> { };
    }
}
