import { List } from "./list.model";

// board.model.ts
export interface Board {
    id?: string;
    name?: string;
    lists?: List[];
  }