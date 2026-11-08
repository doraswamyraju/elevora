import React from 'react';

export const FeaturesGridSection: React.FC = () => {
  const features = [
    {
      icon: "🎙️",
      title: "AI Speaking Tutor",
      desc: "Practice real conversations"
    },
    {
      icon: "▶️",
      title: "Bite-sized Lessons",
      desc: "Short, focused and practical"
    },
    {
      icon: "🎯",
      title: "Real Scenarios",
      desc: "Workplace situations and role-plays"
    },
    {
      icon: "📝",
      title: "Practice & Quizzes",
      desc: "Reinforce and retain learning"
    },
    {
      icon: "📈",
      title: "Progress Tracking",
      desc: "See your improvement week by week"
    },
    {
      icon: "📥",
      title: "Learn Anywhere",
      desc: "On web, Android and iOS"
    }
  ];

  return (
    <section id="features" className="py-16 md:py-24 bg-white text-slate-900">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Header */}
        <div className="text-center max-w-2xl mx-auto mb-14">
          <h2 className="text-3xl sm:text-4xl font-extrabold text-slate-900 font-display">
            Powerful <span className="text-[#F5A623]">Learning Features</span>
          </h2>
          <p className="mt-2 text-xs sm:text-sm text-slate-500">
            Everything you need to speak, practice and improve — all in one place.
          </p>
        </div>

        {/* 6 White Cards with Drop Shadow */}
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-6 gap-4">
          {features.map((feat, idx) => (
            <div 
              key={idx}
              className="bg-white rounded-2xl p-5 border border-slate-200 shadow-[0_8px_30px_rgb(0,0,0,0.06)] flex flex-col items-center text-center hover:-translate-y-1 transition-transform"
            >
              <div className="text-2xl mb-3">
                {feat.icon}
              </div>
              <h3 className="text-xs font-bold text-slate-900 mb-1 leading-tight">
                {feat.title}
              </h3>
              <p className="text-[11px] text-slate-500 leading-snug">
                {feat.desc}
              </p>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
};
