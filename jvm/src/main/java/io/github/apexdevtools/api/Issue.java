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
public abstract class Issue {

    // Default source name for apex-ls generated diagnostics
    public final static String APEX_LS_PROVIDER = "apex-ls";

    // Category constants, these should become enums after moving to Scala3
    public final static String SYNTAX_ISSUE = "Syntax";
    public final static String ERROR_ISSUE = "Error";
    public final static String MISSING_ISSUE = "Missing";
    public final static String WARNING_ISSUE = "Warning";
    public final static String UNUSED_ISSUE = "Unused";

    /* The generator of this issue, default to APEX_LS_PROVIDER */
    public abstract String provider();

    /* The file path where the issue was found */
    public abstract String filePath();

    /* The location within the file */
    public abstract IssueLocation fileLocation();

    /* The rule violated, sets the issue priority */
    public abstract Rule rule();

    /* Is this considered an error issue, rather than a warning */
    public abstract Boolean isError();

    /* The issue message */
    public abstract String message();

    /* Format as String, filePath is omitted to avoid duplicating over multiple Issues */
    public String asString() {
        return rule().name() + ": " + fileLocation().displayPosition() + ": " + message();
    }

    @Override
    public String toString() {
        return filePath() + ": " + asString();
    }
}
