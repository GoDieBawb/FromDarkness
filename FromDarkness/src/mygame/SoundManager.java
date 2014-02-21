/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public class SoundManager extends AbstractAppState {
   
  public    SimpleApplication      app;
  public    AppStateManager        stateManager;
  public    AssetManager           assetManager;
  public    Node                   rootNode;
  public    AudioNode              gunShot;
  public    AudioNode              emptyGun;
  public    AudioNode              missedPunch;
  public    AudioNode              zombieTalk;

@Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.rootNode     = this.app.getRootNode();
    this.assetManager  = this.app.getAssetManager();
    this.stateManager = this.app.getStateManager();
    initAudio();
    System.out.println("AudioManager Attached");
    }

  public void initAudio(){
    gunShot = new AudioNode(assetManager, "Sound/Effects/Gun.wav", false);
    gunShot.setPositional(false);
    gunShot.setLooping(false);
    gunShot.setVolume(2);
    rootNode.attachChild(gunShot);

    emptyGun = new AudioNode(assetManager, "Sounds/Gun/Empty.wav", false);
    emptyGun.setPositional(false);
    emptyGun.setLooping(false);
    emptyGun.setVolume(2);
    rootNode.attachChild(emptyGun);
    
    missedPunch = new AudioNode(assetManager, "Sounds/Punch/Punch.wav", false);
    missedPunch.setPositional(false);
    missedPunch.setLooping(false);
    missedPunch.setVolume(2);
    rootNode.attachChild(missedPunch);
 
    zombieTalk = new AudioNode(assetManager, "Sounds/Monster/zombieTalk.wav", false);
    zombieTalk.setPositional(false);
    zombieTalk.setLooping(false);
    zombieTalk.setVolume(2);
    rootNode.attachChild(zombieTalk);
 
    /* nature sound - keeps playing in a loop. */
    AudioNode audio_nature = new AudioNode(assetManager, "Sound/Environment/Ocean Waves.ogg", true);
    audio_nature.setLooping(true);  // activate continuous playing
    audio_nature.setPositional(true);   
    audio_nature.setVolume(3);
    rootNode.attachChild(audio_nature);
    audio_nature.play(); // play continuously!
    System.out.println("Audio Initialized");
    }
  
  public void playSound(Player player){
    if (player.getItemInHand().equals("Gun"))
      if (player.getAmmo(player) > 0)
      gunShot.playInstance();
      else
      emptyGun.playInstance();
    else
    missedPunch.playInstance();
    }
  
  public void zombieSounds(Monster monster){
    zombieTalk.playInstance();
    }
    
}