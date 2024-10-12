// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "react/common/src/components/VideoAnim/AspectAnim.css";
import AutoVideo from "@components/VideoAnim/AutoVideo.tsx";
import { Fragment } from "react";

type AspectRation = "a1-1" | "a9-16"

type AspectAnimProps = {
    sources: string[],
    ratio: AspectRation
}

function AspectAnim({ sources, ratio }: AspectAnimProps) {
    const video = (src: string, idx: number) => <Fragment key={ idx }>
        <div className="item">
            <AutoVideo src={ src } />
        </div>
    </Fragment>;

    return <>
        <div className="container">
            <div className={ `aspect ${ ratio } fullscreen` }>
                { sources.map(video) }
            </div>
        </div>
    </>;
}

export default AspectAnim;
