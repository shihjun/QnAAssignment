import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, JoinColumn } from "typeorm";
import { Users } from "./Users";
import { Questions } from "./Questions";

@Entity()
export class Answers {

  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  text: string;

  @ManyToOne(type => Users, user => user.answers)
  @JoinColumn({ name: "user_id" })
  user: Users;

  @ManyToOne(type => Questions, question => question.answers)
  @JoinColumn({ name: "question_id" })
  question: Questions;

}