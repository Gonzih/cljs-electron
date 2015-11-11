[![Build Status](https://travis-ci.org/Gonzih/cljs-electron.svg?branch=master)](https://travis-ci.org/Gonzih/cljs-electron)

# Clojurified Electron

![](https://raw.githubusercontent.com/Gonzih/cljs-electron/master/demo.gif)

My attempt to recreate ClojureScript development workflow while developing desktop apps with [electron](http://electron.atom.io/).

## What is currently included

* ClojureScript (init script and ui code)
* Figwheel for interactive development (+ serves webjars)
* Reagent for UI

## Running it

```shell
gem install foreman              # install foreman gem (see Procfile)
npm install electron-prebuilt -g # install electrob binaries

foreman start &                  # compile cljs and start figwheel
electron .                       # start electron
```

## Releasing

```shell
lein cljsbuild once frontend-release # compile ui code
lein cljsbuild once electron-release # compile electron initialization code

electron .                           # start electron to test that everything works
```

After that you can follow [distribution guide for the electron.](https://github.com/atom/electron/blob/master/docs/tutorial/application-distribution.md)
