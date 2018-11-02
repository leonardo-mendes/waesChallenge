path=$(shell pwd)

start:
	docker-compose pull
	docker-compose up -d

stop:
	docker-compose down

restart: | stop start
