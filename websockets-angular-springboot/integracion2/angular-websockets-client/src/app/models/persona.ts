
export class Persona {
    personId?: number;
    nombre: string;
    apellidos: string;
    email: string;
    edad: number;
    direccion: string;
    sexo: number;
    registerDate: Date;

    /* Este metodo devolvera un string */
    toString(): string {
        return `Persona = {
            nombre=${this.nombre},
            apellidos=${this.apellidos},
            email=${this.email},
            edad=${this.edad},
            direccion=${this.direccion},
            registerDate=${this.registerDate}}
        }`;
    }
}

export interface Sexo {
    id: number;
    descripcion: string;
}
