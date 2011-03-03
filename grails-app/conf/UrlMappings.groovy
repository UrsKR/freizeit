class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "urlaub", action: "antrag")
        "500"(view: '/error')
    }
}
