/*
 * Copyright 2009 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.errai.test.persistence.dto;

import java.util.Set;

/**
 * @author: Heiko Braun <hbraun@redhat.com>
 * @date: Jun 16, 2010
 */
public class UserDTO
{
  private String userId;

  private String name;

  private Set<OrderDTO> orders;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<OrderDTO> getOrders()
  {
    return orders;
  }

  public void setOrders(Set<OrderDTO> orders)
  {
    this.orders = orders;
  }
}


