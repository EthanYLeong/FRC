// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj2.command.Command;

public class AutonomousDistance extends SequentialCommandGroup {

  private final Drivetrain drivetrain;

  /**
   * Creates a new Autonomous Drive based on distance. This will drive out for a
   * specified distance,
   * turn around and drive back.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   */
  public AutonomousDistance(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addCommands(new CommandBuilder()
       .move(35)
       .turn(180)
       .move(35)
       .build());
  }

  class CommandBuilder {
    List<Command> commands = new ArrayList<>();

    CommandBuilder move(double inches) {
      commands.add(new DriveDistance(1, inches, drivetrain));
      return this;
    }
    CommandBuilder turn(double degrees) {
      commands.add(new TurnDegrees(1, degrees*1/2, drivetrain));
      return this;
    }
    Command[] build(){

      return commands.toArray(new Command[0]);
    }
  }
}
