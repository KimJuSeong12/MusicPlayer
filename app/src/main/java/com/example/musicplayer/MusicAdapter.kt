package com.example.musicplayer

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.databinding.ActivityMainBinding
import com.example.musicplayer.databinding.ItemRayoutBinding
import java.text.SimpleDateFormat

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.Hodler>() {
    val musicList = mutableListOf<Music>()
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hodler {
        val binding: ItemRayoutBinding =
            ItemRayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Hodler(binding)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: Hodler, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
    }

    inner class Hodler(val binding: ItemRayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        var musicUri: Uri? = null

        init {
            itemView.setOnClickListener {
                if (mediaPlayer != null) {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        fun setMusic(music: Music) {
            musicUri = music.getMusicUri()
            binding.imageAlbum.setImageURI(music.getAlbumUri())
            binding.textArtist.text = music.artist
            binding.textTitle.text = music.title
            val sdf = SimpleDateFormat("mm:ss")
            binding.textDuration.text = sdf.format(music.duration)
        }
    }
}

