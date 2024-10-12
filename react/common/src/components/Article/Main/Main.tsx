// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "./Main.css"
import { PropsWithChildren } from "react";

const Main = ({ children }: PropsWithChildren) => <>
    <section>
        <main>
            <article>
                { children }
            </article>
        </main>
    </section>
</>;

export default Main;
