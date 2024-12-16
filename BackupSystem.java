/*
 * Uma empresa quer implementar um sistema de backup no qual arquivos são
 * guardados em fitas de dados.
 * 
 * O novo sistema deve seguir as duas seguintes regras:
 * 1 - Nunca colocar mais de dois arquivos na mesma fita.
 * 2 - Os arquivos não podem ser divididos entre múltiplas fitas.
 * 
 * É garantido que todas as fitas tenham o mesmo tamanho e que elas sempre serão
 * capazes de guardar o maior arquivo.
 * 
 * Com essas premissas em mente, desenvolva uma solução que seja capaz de contar
 * quantas fitas serão requeridas para guardar o backup da forma mais
 * eficiente. O parâmetro da sua função será uma estrutura que contará o tamanho
 * dos arquivos e a capacidade das fitas. Você deve retornar a quantidade mínima
 * de fitas requeridas para guardar os arquivos.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackupSystem {

  public static class Tape {

    private int capacity;
    private int slots;

    public Tape() {
      this.capacity = 100;
      this.slots = 2;
    }

    public void addInTape(int memory) {
      this.capacity -= memory;
      this.slots--;
    }

    public int getCapacity() {
      return this.capacity;
    }

    public int getSlots() {
      return slots;
    }

  }

  public static int getMinimumTapeCount(List<Integer> batch) {
    // Ordenação do batch e criação da lista de fitas, inicialmente com uma fita
    // vazia.
    batch.sort(null);
    List<Tape> tapes = new ArrayList<>();
    tapes.add(new Tape());

    // 10, 20, 40, 50, 50, 60, 70, 100, 100

    for (int i = batch.size() - 1; i >= 0; i--) {
      Integer currentBatch = batch.get(i);
      boolean added = false;

      for (Tape tape : tapes) {
        // Verifica se há espaço e capacidade sufiente na fita e aloca em caso positivo.
        if (tape.getSlots() > 0 && tape.getCapacity() >= currentBatch) {
          tape.addInTape(currentBatch);
          added = true;
          break;
        }
      }

      // Se o valor não foi adicionado em nenhuma fita, criar uma nova e aloca-lo.
      if (!added) {
        Tape tape = new Tape();
        tape.addInTape(currentBatch);
        tapes.add(tape);
      }
    }

    return tapes.size();
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.addAll(Arrays.asList(70, 10, 20, 40, 50, 60, 50, 100, 100));

    System.out.println("Programa iniciado!");
    System.out.println("A quantidade mínima de fitas necessárias para alocar os itens é: " + getMinimumTapeCount(list));
  }

}