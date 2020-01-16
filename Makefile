DOCKER_IMAGE = clojure:latest

ci-build:
	env LEIN_ROOT=nbd  JVM_OPTS=-Xmx3200m \
		lein do clean, \
		cljsbuild once electron-dev, \
		cljsbuild once frontend-dev, \
		clean, \
		cljsbuild once electron-release, \
		cljsbuild once frontend-release

ci:
	docker run --rm -t -v $(shell pwd):/var/code $(DOCKER_IMAGE) 'cd /var/code && make ci-build'
