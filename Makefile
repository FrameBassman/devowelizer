application:
	docker compose \
		--project-directory=${PWD} \
		--project-name=devowelizer \
		-f deploy/docker-compose.yml \
		up -d

stop-application:
	docker compose \
		--project-directory=${PWD} \
		--project-name=devowelizer \
		-f deploy/docker-compose.yml \
		down
