package uz.Ilhomjon.Baholash.models

class Pupil {
    var name:String =""
    var grade:Int =0
    var list = arrayListOf<Boolean>(false, false, false, false)

    constructor(name: String, grade: Int, list: ArrayList<Boolean>) {
        this.name = name
        this.grade = grade
        this.list = list
    }

    constructor()

    override fun toString(): String {
        return "Pupil(name=$name, grade=$grade, list=$list)"
    }
}