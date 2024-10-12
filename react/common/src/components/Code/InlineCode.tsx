// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import { ReactNode } from "react";

type InlineCodeProps = {
    children: ReactNode;
    backgroundColor?: string;
}

function InlineCode({ children, backgroundColor }: InlineCodeProps) {
    return <>
        <code
            className="code language-plaintext highlighter-rouge"
            style={ { backgroundColor } }
        >
            { children }
        </code>
    </>;
}

export default InlineCode;
