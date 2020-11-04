package pojo

class ModelClass {
    lateinit var name:String
    lateinit var poster_path:String
    var id:Int = 0

    constructor()

    constructor(name: String, poster_path: String, id: Int) {
        this.name = name
        this.poster_path = poster_path
        this.id = id
    }
}