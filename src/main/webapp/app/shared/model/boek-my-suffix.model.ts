export interface IBoekMySuffix {
  id?: number;
  titel?: string;
  auteur?: string;
  paginas?: number;
  korteInhoud?: string;
}

export class BoekMySuffix implements IBoekMySuffix {
  constructor(public id?: number, public titel?: string, public auteur?: string, public paginas?: number, public korteInhoud?: string) {}
}
