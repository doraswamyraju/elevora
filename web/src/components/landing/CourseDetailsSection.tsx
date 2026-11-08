import React from 'react';

interface CourseDetailsSectionProps {
  onStartCourse: () => void;
}

export const CourseDetailsSection: React.FC<CourseDetailsSectionProps> = ({ onStartCourse }) => {
  const steps = [
    {
      num: 1,
      title: "Building Confidence & Core Workplace English",
      weeks: "Weeks 1 – 4",
      highlight: true
    },
    {
      num: 2,
      title: "Workplace Conversation & Team Communication",
      weeks: "Weeks 5 – 8",
      highlight: false
    },
    {
      num: 3,
      title: "Managing People, Meetings & Performance",
      weeks: "Weeks 9 – 12",
      highlight: false
    },
    {
      num: 4,
      title: "Emails, Reports & Presentations",
      weeks: "Weeks 13 – 16",
      highlight: false
    }
  ];

  return (
    <section id="courses" className="py-16 md:py-24 bg-[#081124] text-white overflow-hidden">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Section Heading */}
        <div className="text-center max-w-2xl mx-auto mb-14">
          <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display">
            Our <span className="text-[#F5A623]">Learning Journey</span>
          </h2>
          <p className="mt-2 text-sm text-slate-300">
            A 4-Module program to build complete workplace communication skills.
          </p>
        </div>

        {/* 4 Cards and Career Progression Steps */}
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-6 items-end">
          
          {/* 4 Step Cards */}
          <div className="lg:col-span-8 grid grid-cols-1 sm:grid-cols-4 gap-3.5">
            {steps.map((s) => (
              <div
                key={s.num}
                onClick={onStartCourse}
                className={`cursor-pointer rounded-2xl p-4 flex flex-col justify-between min-h-[190px] border transition-transform hover:-translate-y-1 relative ${
                  s.highlight 
                    ? 'bg-[#0E1A33] border-[#F5A623] shadow-lg shadow-amber-500/10' 
                    : 'bg-[#0B1528] border-slate-700/70 hover:border-slate-500'
                }`}
              >
                <div>
                  {/* Step Number Circle */}
                  <div className="flex items-center justify-between mb-3">
                    <span className="w-6 h-6 rounded-full bg-[#F5A623] text-slate-950 font-black text-xs flex items-center justify-center">
                      {s.num}
                    </span>
                    <span className="text-slate-400 text-sm">
                      {s.num === 1 ? '👥' : s.num === 2 ? '💬' : s.num === 3 ? '📋' : '📊'}
                    </span>
                  </div>

                  <h3 className="text-xs font-bold text-white leading-snug">
                    {s.title}
                  </h3>
                </div>

                <div className="mt-3">
                  <span className="inline-block px-2.5 py-1 rounded-full bg-[#12203D] border border-slate-700 text-[10px] font-semibold text-slate-300">
                    {s.weeks}
                  </span>
                </div>
              </div>
            ))}
          </div>

          {/* Stepping Man with Trophy Graphic matching exact reference */}
          <div className="lg:col-span-4 flex justify-center">
            <div className="w-full max-w-[340px] rounded-2xl overflow-hidden shadow-xl border border-slate-700/60">
              <img 
                src="/assets/career-ladder-trophy.jpg" 
                alt="Executive Career Escalation" 
                className="w-full h-auto object-cover" 
              />
            </div>
          </div>

        </div>

      </div>
    </section>
  );
};
