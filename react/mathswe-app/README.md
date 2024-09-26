<!-- Copyright (c) {{copyright-year}} Tobias Briones. All rights reserved. -->
<!-- SPDX-License-Identifier: {{spdx-license-id}} -->
<!-- This file is part of {{gh-repo-url}} -->

# {{project-name}}

[![Project](https://mathswe-ops-services.tobiasbriones-dev.workers.dev/badge/project/{{project-id}})]({{project-url}})
&nbsp;
[![GitHub Repository](https://img.shields.io/static/v1?label=GITHUB&message=REPOSITORY&labelColor=555&color=0277bd&style=for-the-badge&logo=GITHUB)](https://github.com/{{gh-org}}/{{repo}}{{path}})

[![GitHub Project License](https://img.shields.io/github/license/{{gh-org}}/{{repo}}.svg?style=flat-square)](https://github.com/{{gh-org}}/{{repo}}/blob/main/LICENSE)

[![GitHub Release](https://mathswe-ops-services.tobiasbriones-dev.workers.dev/badge/version/github/{{gh-org}}/{{repo}})](https://github.com/{{gh-org}}/{{repo}}/releases/latest)

⚙ Production
[![Netlify Status]({{prod-badge}})]({{prod-badge-url}})
| Staging
[![Netlify Status]({{staging-badge}})]({{staging-badge-url}})

{{project-def}}

## Template Setup

To apply this template, you must replace the variables and perform some steps to
adapt it to the MathSwe project.

All variables are within `{{}}` doubled braces.

### Template Variables

Replace the variables in the template files:

**Base variables:**

- **project-id**
- **project-name**
- **project-url** (production)
- **project-def** (i.e., abstract or one paragraph description)
- **project-description-short**
- **copyright-year**
- **license-name**
- **gh-repo-url**
- **gh-org**
- **repo**
- **path** (GitHub repo absolute path or empty for the repo root, e.g.,
  `/blob/main/math.software---mvp`, or ``)
- **prod-badge** (e.g., Netlify)
- **prod-badge-url**
- **staging-badge**
- **staging-badge-url**

**Advanced variables:**

- **meta-title**
- **meta-description**
- **meta-keywords**
- **meta-theme-color**
- **app-title**
- **app-icon-svg** (from `/public`)
- **app-icon-192-png** (from `/public`)
- **app/main.tsx** (`app/main.tsx` for monoliths and `main.tsx` for 
  microfrontends)
- **app-name**
- **mvp-version-label** (can be either `` or `MVP ` with a trailing space, 
  so it becomes `MVP v0.1.0-dev`)
- **spdx-license-id**

### Installation

Execute:

**For most apps:**

- `npm i bootstrap`
- `npm i react-bootstrap`
- `npm i better-react-mathjax`

**For monoliths or microfrontends requiring specific icons:**

- `npm i @fortawesome/react-fontawesome`
- `npm i @fortawesome/free-brands-svg-icons`
- `npm i @fortawesome/free-solid-svg-icons`

### Update

Update the `app` path if your application is a monolith: `tsconfig.app.json`,
and `vite.config.ts`.

- From `"@app/*": [ "src/*" ]` to `"@app/*": [ "src/app/*" ]`.
- From `"@app": resolve(__dirname, "src"),` to `"@app": resolve(__dirname, 
"src/app"),`.

⚠️ If your IDE doesn't recognize path aliases, you can copy them from `tsconfig.
app.json` to `tsconfig.node.json` and it should fix it. If you remove them from
`tsconfig.app.json` the build will fail, so you will have to maintain both
versions until a better fix shows up.

Update `main.ts` so it looks like:

```ts
import App from "@app/App.tsx";
import MathJaxContext from "better-react-mathjax/MathJaxContext";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

const mathJaxConfig = {
    loader: { load: [ "input/asciimath" ] },
};

const rootEl = document.getElementById("root");

if (rootEl === null) {
    console.error("Root element not found");
}
else {
    render(rootEl);
}

function render(rootEl: HTMLElement) {
    createRoot(rootEl)
        .render(
            <StrictMode>
                <MathJaxContext config={ mathJaxConfig }>
                    <App />
                </MathJaxContext>
            </StrictMode>,
        );
}
```

Update the `package.json` when needed, like `name` and `"version": "0.1.0-dev"`.

### Final Step

Delete dummy Vite files, like icons from `/public` and `/src/assets`. Delete 
vite code, like `index.css`, `App.css`.

Update `App.test.tsx` with your initial application specs.

Delete the `Template Setup` section of this `README.md` after you set up your
project.

## Getting Started

Install project dependencies via `npm install`.

Run development mode via `npm run dev`.

Build for production via `npm run build`.

Run ESLint via `npx eslint .`.

## Contact

Tobias Briones: [GitHub](https://github.com/tobiasbriones)
[LinkedIn](https://linkedin.com/in/tobiasbriones)

## About

**{{project-name}}**

{{project-description-short}}

Copyright © {{copyright-year}} Tobias Briones. All rights reserved.

### License

This project is licensed under the [{{license-name}}](LICENSE).
