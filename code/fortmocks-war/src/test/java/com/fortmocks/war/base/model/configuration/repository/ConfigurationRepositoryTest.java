/*
 * Copyright 2015 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fortmocks.war.base.model.configuration.repository;

import com.fortmocks.war.base.config.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Karl Dahlgren
 * @since 1.0
 * @see com.fortmocks.war.base.model.configuration.repository.ConfigurationRepository
 * @see com.fortmocks.war.base.model.configuration.repository.ConfigurationRepositoryImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ConfigurationRepositoryTest {

    @Autowired
    private ConfigurationRepository configurationRepository;
    protected MockMvc mockMvc;

    @Before
    public void initiateTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){
        Assert.assertNotNull(configurationRepository);
    }
}