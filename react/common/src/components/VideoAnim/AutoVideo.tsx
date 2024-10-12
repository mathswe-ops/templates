// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: AGPL-3.0-or-later
// This file is part of https://github.com/texsydo/texsydo---mvp

import "react/common/src/components/VideoAnim/AutoVideo.css"

type AutoVideoProps = {
    src: string,
    caption?: string,
}

function AutoVideo({ src, caption }: AutoVideoProps) {
    return <>
        <figure>
            <video autoPlay muted loop playsInline>
                <source
                    src={ src }
                    type="video/mp4"
                />
                Your browser does not support the video tag.
            </video>

            { caption &&
              <figcaption>{ caption }</figcaption>
            }
        </figure>
    </>;
}

export default AutoVideo;
