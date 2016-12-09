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
npm install electron-prebuilt -g # install electron binaries

foreman start                    # compile cljs and start figwheel
electron .                       # start electron from another terminal
```

## Releasing

```shell
lein cljsbuild once frontend-release # compile ui code
lein cljsbuild once electron-release # compile electron initialization code

electron .                           # start electron to test that everything works
```

After that you can follow [distribution guide for the electron.](https://github.com/atom/electron/blob/master/docs/tutorial/application-distribution.md)

The easiest way to package an electron app is by using [electron-packager](https://github.com/maxogden/electron-packager):

```shell
npm install electron-packager -g                                            # install electron packager
electron-packager . HelloWorld --platform=darwin --arch=x64 --version=1.4.8 # package it!
```
