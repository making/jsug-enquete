#!/bin/sh
fly -t jjug sp -p jjug-enquete -c pipeline.yml -l ./credentials.yml
