export interface IPersoonMySuffix {
  id?: number;
  naam?: string;
  voornaam?: string;
  wenslijstId?: number;
  boekenlijstId?: number;
  gelezenId?: number;
}

export class PersoonMySuffix implements IPersoonMySuffix {
  constructor(
    public id?: number,
    public naam?: string,
    public voornaam?: string,
    public wenslijstId?: number,
    public boekenlijstId?: number,
    public gelezenId?: number
  ) {}
}
