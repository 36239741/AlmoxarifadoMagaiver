import { Fornecedor } from './fornecedor';

export class Item {
  codigo: string;
  descricao: string;
  quantidade: number;
  localArmazenamento: string;
  valor: number;
  quantidadeRetirada: number;
  fornecedor: Fornecedor;
  itemStatus: boolean;
  id: number;
  valorTotal: number;
}
