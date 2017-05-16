[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/Gonzih/feeds2imap.clj/blob/master/LICENSE.md) [![Build Status](https://travis-ci.org/Gonzih/cljs-electron.svg?branch=master)](https://travis-ci.org/Gonzih/cljs-electron)

# Clojurified Electron

![](https://raw.githubusercontent.com/Gonzih/cljs-electron/master/demo.gif)

My attempt to recreate ClojureScript development workflow while developing desktop apps with [electron](http://electron.atom.io/).

## What is currently included

* ClojureScript (init script and ui code)
* Figwheel for interactive development
* Reagent for UI
* Advanced compilation with externs inference in release compilation targets

## Running it

```shell
npm install electron-prebuilt -g # install electron binaries

lein cooper                      # compile cljs and start figwheel
electron .                       # start electron from another terminal
```

## Releasing

```shell
lein do clean, cljsbuild once frontend-release, cljsbuild once electron-release
electron . # start electron to test that everything works
```

After that you can follow [distribution guide for the electron.](https://github.com/atom/electron/blob/master/docs/tutorial/application-distribution.md)

The easiest way to package an electron app is by using [electron-packager](https://github.com/maxogden/electron-packager):

```shell
npm install electron-packager -g                                            # install electron packager
electron-packager . HelloWorld --platform=darwin --arch=x64 --version=1.4.8 # package it!
```
