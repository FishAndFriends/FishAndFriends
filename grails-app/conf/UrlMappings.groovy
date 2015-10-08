class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


//        "/"(view:"/index")
        // http://stackoverflow.com/a/5910294
        "/"(controller: 'login', action: 'index')

        "500"(view:'/error')
	}
}
