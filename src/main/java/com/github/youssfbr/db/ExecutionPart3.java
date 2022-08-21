package com.github.youssfbr.db;

import com.github.youssfbr.db.entities.Aluno;
import com.github.youssfbr.db.entities.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExecutionPart3 {

    public static void main(String[] args) {

        // 1 - Dados instanciados para serem utilizados como exemplo
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoParaAdicionar = new Estado("Fortaleza", "CE");
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new Estado("Caucaia", "CE"));
        entityManager.persist(new Aluno("Alisson", 29, estadoParaAdicionar));
        entityManager.persist(new Aluno("Link", 10, estadoParaAdicionar));
        entityManager.persist(new Aluno("Lilica", 9, estadoParaAdicionar));
        entityManager.getTransaction().commit();

        // 2 - Vamos utilizar o método do EntityManager find(), SQL nativo, JPQL e JPA Criteria API para realizar uma
        // consulta que retornarar o mesmo valor equivalente aos seguintes SQL:
        // SELECT * FROM Aluno WHERE id = 1 (Equivalente ao método find do entityManager na parte 2.2)
        // SELECT * FROM Aluno WHERE nome = 'Link' (Sera o equivalente para as outras consultas nas partes 2.3 - 2.4 - 2.5)

        // 2.1 O parametro de busca que será utilizado nas proximas consultas
        String nome = "Alisson";

        // =============================================================================================================

        // 2.2 - Utilizando o método find do entityManager
        // Trazendo somente 1 resultado
        // Aluno alunoEntityManager = entityManager.find(Aluno.class, 1);

        // Trazendo uma lista como resultado
        // Nao eh possivel!!! Deve utilizar um dos métodos utilizados abaixos nas partes 2.3 - 2.4 - 2.5

        // Resultados das consultas acima
        // System.out.println("Consulta alunoEntityManager: " + alunoEntityManager);

        // =============================================================================================================

        // 2.3 - SQL nativo

//        // Trazendo somente 1 resultado
//        String sql = "SELECT * FROM Aluno WHERE nome = :nome ";
//        Aluno alunoSQL = (Aluno) entityManager
//                .createNativeQuery(sql, Aluno.class)
//                .setParameter("nome", nome)
//                .getSingleResult();
//
//        // Trazendo uma lista como resultado
//        String sqlList = "SELECT * FROM Aluno";
//        List<Aluno> alunoSQLList = entityManager
//                .createNativeQuery(sqlList, Aluno.class)
//                .getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Consulta alunoSQL: " + alunoSQL);
//        System.out.println("-------------------------------");
//        alunoSQLList.forEach(Aluno -> System.out.println("Consulta alunoSQLList: " + Aluno));

        // =============================================================================================================

        // 2.4 - JPQL

        // Trazendo somente 1 resultado
        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();

        // Trazendo uma lista como resultado
        String jpqlList = "select a from Aluno a where a.estado = :estado";
        List<Aluno> alunoJPQLList = entityManager
                .createQuery(jpqlList, Aluno.class)
                .setParameter("estado", estadoParaAdicionar)
                .getResultList();

        // Resultados das consultas acima
        System.out.println("Consulta alunoJPQL: " + alunoJPQL);
        alunoJPQLList.forEach(Aluno -> System.out.println("Consulta alunoJPQLList: " + Aluno));

        // =============================================================================================================

        // 2.5 - JPA Criteria API + JPA Metamodel

//        // Trazendo somente 1 resultado
//        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
//        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
//        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
//        inClause.value(nome);
//        criteriaQuery.select(alunoRoot).where(inClause);
//        Aluno alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();
//
//        // Trazendo uma lista como resultado
//        CriteriaQuery<Aluno> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
//        Root<Aluno> alunoRootList = criteriaQueryList.from(Aluno.class);
//        List<Aluno> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();
//
//        // Resultados das consultas acima
//        System.out.println("Consulta alunoAPICriteria: " + alunoAPICriteria);
//        alunoAPICriteriaList.forEach(Aluno -> System.out.println("Consulta alunoAPICriteriaList: " + Aluno));

    }

}

