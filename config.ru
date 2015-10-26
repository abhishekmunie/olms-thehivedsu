require "rack/jekyll"

run Rack::Jekyll.new(:auto => true)
