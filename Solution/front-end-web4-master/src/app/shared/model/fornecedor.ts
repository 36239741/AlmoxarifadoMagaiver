import { Telefone } from './telefone';
import { Item } from './item';
export class Fornecedor {
  id: number;
  nome: string;
  email: string;
  telefone?: (Telefone)[] | null;
  item?: (Item)[] | null;
  cep: string;
  longadouro: string;
  numero: number;
  bairro: string;
  cidade: string;
  estado: string;
  pais: string;
  fornecedorStatus: boolean;
}
