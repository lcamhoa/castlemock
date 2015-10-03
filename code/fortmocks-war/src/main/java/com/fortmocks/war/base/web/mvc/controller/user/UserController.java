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

package com.fortmocks.war.base.web.mvc.controller.user;

import com.fortmocks.core.base.model.user.dto.UserDto;
import com.fortmocks.core.base.model.user.service.UserService;
import com.fortmocks.war.base.web.mvc.controller.AbstractViewController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The UserController provides functionality to retrieve user information.
 * @author Karl Dahlgren
 * @since 1.0
 */
@Controller
@Scope("request")
@RequestMapping("/web/user")
public class UserController extends AbstractViewController {

    private static final String PAGE = "core/user/user";

    @Autowired
    private UserService userService;

    /**
     * The method retrieves a specific user with the provided user id. The retrieved
     * user will be returned with a view and displayed for the Fort Mocks' user.
     * @param userId The id of the user that will be retrieved
     * @return A view that displays the retrieved user
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ModelAndView defaultPage(@PathVariable final Long userId) {
        final UserDto userDto = userService.findOne(userId);
        final ModelAndView model = createPartialModelAndView(PAGE);
        model.addObject(USER, userDto);
        return model;
    }

}