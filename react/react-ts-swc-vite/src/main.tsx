import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.tsx";
import "./index.css";

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
                <App />
            </StrictMode>,
        );
}
