/*
 * Copyright (C) 2020 Aviator
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

//loop with dollar side
var data = [
    {
        "val": 1
    },
    {
        "val": 2
    },
    {
        "val": 4
    },
    {
        "val": 3
    }
];

data.forEach(dt => {
    console.log('Number value ${dt.val}' + dt.val);
});

//prototype add attribute
function animal(breed) {
    this.breed = breed;
}
//var cow=new animal("cow");
animal.prototype.sound = "woow woow";
console.log(animal.prototype.sound);

var shape = {
    types: [
        "Rectangle",
        "Triangle",
        "Circle",
        "Square"
    ],
    name: "shapeName",
    numSides: 0,
    area: function (pos, l, w) {
        this.name = this.types[pos];
        switch (pos) {
            case 0:
                this.numSides = 4;
                return (l * w);
            case 1:
                this.numSides = 3;
                return (0.5 * l * w);
            case 2:
                this.numSides = 0;
                return ((Math.PI) * (Math.pow((l / 2), 2)));

            case 3:
                this.numSides = 4;
                return Math.pow(l, 2);

            default:
                return "Unknown Shape";
                break;
        }
    }
};


//var shapeTest = new shape();
//shape.prototype.perimeter = function (pos, l, w, h) {
//    this.name = this.types[pos];
//    switch (pos) {
//        case 0:
//            this.numSides = 4;
//            return (l * w) * 2;
//        case 1:
//            this.numSides = 3;
//            return (l + w + h);
//        case 2:
//            this.numSides = 0;
//            return ((Math.PI) * l);
//
//        case 3:
//            this.numSides = 4;
//            return 4 * (l);
//
//        default:
//            return "Unknown Shape";
//    }
//};

var s1 = shape.area(0, 5, 6, 2);
//var s2 = shape.perimeter(0, 5, 6, 2);
console.log(shape.name + "-" + shape.numSides + "-Area: " + s1);
//console.log(shape.name + "-" + shape.numSides + "-" + s1);

//call and apply
var person = {
    details: function (age, gender) {
        return this.firstName + " " + age + " " + gender;
    }
};

var p = {
    firstName: "Yonah"
};

console.log(person.details.call(p, 20, "M"));
console.log(person.details.apply(p, [20, "M"]));

//var http = new XMLHttpRequest();