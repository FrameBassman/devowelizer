application:
	docker compose \
		--project-directory=${PWD} \
		--project-name=$(shell basename $(CURDIR)) \
		-f deploy/docker-compose.yml \
		up -d
