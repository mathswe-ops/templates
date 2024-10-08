// Copyright (c) 2024 Tobias Briones. All rights reserved.
// This file is part of https://github.com/mathswe/prototypes

package mathswekt

/**
 * It loads the given relative `path` from the application `resources`
 * directory.
 *
 * The application must be JPMS (use Java modules).
 */
fun resPath(path: String): String = object {}
    .javaClass
    .getResource("/$path")
    ?.toURI()
    .toString()
