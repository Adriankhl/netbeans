/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.java.hints.bugs;

import org.netbeans.junit.NbTestCase;
import org.netbeans.modules.java.hints.test.api.HintTest;

/**
 *
 * @author lahvac
 */
public class CheckReturnValueHintTest extends NbTestCase {

    public CheckReturnValueHintTest(String name) {
        super(name);
    }

    public void testSimpleReport() throws Exception {
        HintTest
                .create()
                .input("package test;\n" +
                       "public class Test {\n" +
                       "    public @CheckReturnValue String foo() { return null; }\n" +
                       "    public void test() {\n" +
                       "        foo();\n" +
                       "    }\n" +
                       "}\n" +
                       "@interface CheckReturnValue {}\n")
                .run(CheckReturnValueHint.class)
                .assertWarnings("4:8-4:14:verifier:ERR_org.netbeans.modules.javahints.CheckReturnValueHint");
    }

    public void testStringSubString() throws Exception {
        HintTest
                .create()
                .input("package test;\n" +
                       "public class Test {\n" +
                       "    public void test(String arg) {\n" +
                       "        arg.substring(0);\n" +
                       "    }\n" +
                       "}\n")
                .run(CheckReturnValueHint.class)
                .assertWarnings("3:8-3:25:verifier:ERR_org.netbeans.modules.javahints.CheckReturnValueHint");
    }
}
