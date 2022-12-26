/*
 Copyright (c) 2021 Kevin Jones, All rights reserved.
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.
 */

package io.github.apexdevtools.api;

/* WARNING: This must be identical to Scala class of same name */
public interface Rule {
    // Priority constants, these should become enums after moving to Scala3
    // These are based on SonarQube, PMD uses Integer 1-5
    Integer BLOCKER_PRIORITY  = 1; // Change absolutely required
    Integer CRITICAL_PRIORITY = 2; // Change highly recommended
    Integer MAJOR_PRIORITY    = 3; // Change recommended
    Integer MINOR_PRIORITY    = 4; // Change optional
    Integer INFO_PRIORITY     = 5; // Change highly optional

    /* The nane of the rule */
    String name();

    /* Range 1-5, 1 being the highest */
    Integer priority();
}
